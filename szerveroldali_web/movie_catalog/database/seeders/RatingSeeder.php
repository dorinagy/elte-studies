<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use App\Models\Rating;

class RatingSeeder extends Seeder {
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run() {
        RatingFactory::new()->count(5)->create();
        // factory(Rating::class, 5)->create();
    }
}
