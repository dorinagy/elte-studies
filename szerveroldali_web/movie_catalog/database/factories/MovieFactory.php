<?php

namespace Database\Factories;

use App\Models\Movie;
use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Str;

class MovieFactory extends Factory {
    /**
     * The name of the factory's corresponding model.
     *
     * @var string
     */
    protected $model = Movie::class;

    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition() {
        return [
            'title' => Str::ucfirst(join(' ', $this->faker->words(random_int(1,3)))),
            'director' => $this->faker->name(),
            'description' => $this->faker->paragraph(random_int(1,4)),
            'year' => random_int(1888, now()->year),
            'length' => $this->faker->numberBetween(3600, 18000),
            'image' => 'movie.png',
            'ratings_enabled' => $this->faker->boolean(),        ];
    }
}
