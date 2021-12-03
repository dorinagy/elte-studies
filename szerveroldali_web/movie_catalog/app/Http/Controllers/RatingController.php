<?php

namespace App\Http\Controllers;

use App\Models\Movie;
use App\Models\Rating;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class RatingController extends Controller {

    public function __construct() {
        $this->middleware('auth')->only(['create', 'save', 'destroyAll']);
    }

    public function create($movie_id) {

        return view('ratings.create', ['movie_id' => $movie_id]);
    }

    public function save(Request $request, $movie_id) {

        $data = $request->validate([
            'rating' => 'required |numeric|min:1|max:5',
            'comment' => 'max:255',
        ], [
            'rating.required' => 'Értékelés megadása kötelező.',
            'rating.min' => 'A minimum adható értékelés: 1.',
            'rating.max' => 'A maximum adható értékelés: 5.'
        ]);

        $data['user_id'] = Auth::id();
        $data['movie_id'] =  $movie_id;

        $rating = Rating::create($data);

        $movie = Movie::withTrashed()->find($movie_id);

        $request->session()->flash('rating_created', true);

        return redirect()->route('movies.show', $movie);
    }

    public function destroyAll($movie_id, Request $request) {

        if (! Auth::user()->is_admin) {
            return abort(403);
        }

       $movie = Movie::withTrashed()->find($movie_id);

        $movie->ratings->each(function ($rating) {
            $this->authorize('delete', $rating);
            $rating->delete();
        });

        $request->session()->flash('all_ratings_deleted', true);

        return redirect()->route('movies.show', $movie);
    }
}
