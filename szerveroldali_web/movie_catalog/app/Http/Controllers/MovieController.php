<?php

namespace App\Http\Controllers;

use App\Models\Movie;
use App\Models\Rating;
use App\Models\User;
use Illuminate\Http\Request;

class MovieController extends Controller {

    public function __construct() {
        $this->middleware('auth')->only(['create', 'store', 'destroy', 'update', 'edit']);
    }

    public function index() {
        $movies = Movie::paginate(10);
        $ratings = Rating::all();
        return view('movies.index', compact('movies', 'ratings'));
    }

    public function details(Movie $movie) {
        $ratings = Rating::all();
        $users = User::all();
        return view('movies.details', compact('movie', 'ratings', 'users'));
    }
}
