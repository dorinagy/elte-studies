<?php

namespace Database\Factories;

use App\Models\Rating;
use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Str;

class RatingFactory extends Factory {
    /**
     * The name of the factory's corresponding model.
     *
     * @var stringP
     */
    protected $model = Rating::class;

    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition() {
        return [
            'rating' => random_int(1,5),
            'comment' => $this->faker->paragraph(random_int(1,3)),
            'created_at' => $this->faker->date($format = 'Y-m-d H:m:s', $max = 'now'),
            'updated_at' => $this->faker->date($format = 'Y-m-d H:m:s', $max = 'now')
        ];
    }

}
