<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;

use \App\Models\Movie;
use \App\Models\Rating;

class DatabaseSeeder extends Seeder {
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run() {
        $faker = \Faker\Factory::create();

        // \App\Models\User::factory(10)->create();
        $this->call(MovieSeeder::class);
        $this->call(RatingSeeder::class);

        /* for ($i = 1; $i <= Post::count(); $i++) {
            Post::find($i)->categories()->attach([
                $faker->numberBetween(1, Category::count())
            ]);
            // Post::find($i)->detach()
        }
 */
    }
}