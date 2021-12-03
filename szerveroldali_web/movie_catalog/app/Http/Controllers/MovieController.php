<?php

namespace App\Http\Controllers;

use App\Models\Movie;
use App\Models\Rating;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Storage;

class MovieController extends Controller {

    public function __construct() {
        $this->middleware('auth')->only(['index', 'create', 'show', 'save', 'destroy', 'update', 'edit', 'restore']);
    }

    public function index() {
        $movies = Movie::paginate(10); // csak 10 film / oldal
        $ratings = Rating::all();
        return view('movies.index', compact('movies', 'ratings'));
    }

    public function show(Movie $movie) {
        $ratings = Rating::all();
        $users = User::all();
        $movie = Movie::withTrashed()->find($movie->id);
        return view('movies.show', compact('movie', 'ratings', 'users'));
    }

    public function create() {
        return view('movies.create');
    }

    public function edit($movie_id) {
        $movie = Movie::withTrashed()->find($movie_id);
        return view('movies.edit', compact('movie'));
    }

    public function toplist() {
        $users = User::all();
        $movies = Movie::all();

        $moviesDesc = $movies->sortByDesc(function ($movie) {
            return $movie->ratings->avg('rating');
        });

        $moviesDesc = $moviesDesc->take(6);

        return view('movies.toplist', compact('moviesDesc'));
    }

    public function validateData(Request $request) {
        return $request->validate([
            'title' => 'required | max:255',
            'director' => 'required | max:128',
            'year' => 'required | integer | min: 1870 | max:'. date('Y'),
            'description' => 'nullable | max : 512',
            'length' =>'required | integer',
            'image' => 'nullable|file|mimes:jpg,png|max:2048'

        ], [
            'title.required' => 'A cím megadása kötelező',
            'title.max' => 'A cím hossza max 255 karakter',
            'director.required' => 'A rendező megadása kötelező',
            'director.max' => 'A rendező hossza max 255 karakter',
            'year.required' => 'Az év megadása kötelező',
            'year.integer' => 'Az évnek egész számnak kell lennie',
            'year.min' => 'Az év minimum 1870',
            'year.max' => 'Az év maximum '. date('Y'),
            'description.max' => 'A leírás hossza max 512 karakter',
            'length.required' => 'A film hosszának megadása kötelező',
            'length.integer' => 'A film hosszának egész számnak kell lennie',
            'image.mimes' => 'Csak a jpg és png fájltípusok elfogadottak',
            'image.max' => 'A kép mérete maximum 2MB'

        ]);
    }

    public function save(Request $request) {

        $data = $this->validateData($request);

        if ($request->hasFile('image')) {
            $file = $request->file('image');
            $data['image'] = $file->hashName();
            Storage::disk('public')->put('images/' . $data['image'], $file->get());
        }

        $movie = Movie::create($data);

        $request->session()->flash('movie_created', true);

        return redirect()->route('movies.show', $movie);
    }

    public function update(Request $request, $movie_id) {

        $movie = Movie::withTrashed()->find($movie_id);

        $data = $this->validateData($request);

        if ($request->has('delete_image') && $request->delete_image){
            $data['delete_image'] = true;
            Storage::disk('public')->delete('images/' . $movie->image);
            $data['image'] = null;
        } else {
            $data['image'] = $movie->image;
        }

        if ($request->hasFile('image')) {
            $file = $request->file('image');
            $data['image'] = $file->hashName();

            if ($movie->image) {
                Storage::disk('public')->delete('images/' . $movie->image);
            }
            Storage::disk('public')->put('images/' . $data['image'], $file->get());
        }

        $movie->update($data);

        $request->session()->flash('movie_edited', true);

        return redirect()->route('movies.show', $movie->id);
    }

    public function destroy(Movie $movie, Request $request) {
        if (Auth::user()->is_admin === 0) {
            return abort(403);
        }

        $this->authorize('delete', $movie);

        $movie = $movie->delete();

        $request->session()->flash('movie_deleted', true);

        return redirect()->route('movies.show', $movie);
    }

    public function restore($movie_id, Request $request) {
        $movie = Movie::withTrashed()->find($movie_id);
        
        $movie = $movie->restore();

        $request->session()->flash('movie_restored', true);

        return redirect()->route('movies.show', $movie);
    }
}
