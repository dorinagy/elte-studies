<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
// use App\Models\Movie;
use Database\Factories\MovieFactory;

class MovieSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        MovieFactory::new()->count(5)->create();
        // factory(Movie::class, 5)->create();
    }
}
