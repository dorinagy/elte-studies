<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Database\Factories\MovieFactory;

class MovieSeeder extends Seeder {
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run() {
        MovieFactory::new()->count(15)->create();
    }
}
