<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class RatingController extends Controller {

    public function create() {
        return view('ratings.create');
    }
}
