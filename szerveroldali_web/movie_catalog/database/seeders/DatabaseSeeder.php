<?php

namespace Database\Seeders;

use App\Models\User;
use \App\Models\Movie;
use \App\Models\Rating;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class DatabaseSeeder extends Seeder {
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run() {
        DB::table('users')->truncate();
        DB::table('movies')->truncate();
        DB::table('ratings')->truncate();

        $this->call(UserSeeder::class);
        $this->call(MovieSeeder::class);
        $this->call(RatingSeeder::class);
        
        $movies = Movie::all();     // Movie 1  -  N  Rating
        $users = User::all();       // User  1  -  N  Rating

        Rating::all()->each(function ($rating) use (&$movies, &$users) {
            if ($users->isNotEmpty() && $movies->isNotEmpty()) {
                $randomUser = $users->random();
                $randomMovie = $movies->random();

                while ($randomUser->ratings->contains('movie_id',  $randomMovie->id)) {
                    $randomUser = $users->random();
                    $randomMovie = $movies->random();
                }

                $rating->user()->associate($randomUser);
                $rating->movie()->associate($randomMovie);
                $rating->save();
            }
        });
    }
}
