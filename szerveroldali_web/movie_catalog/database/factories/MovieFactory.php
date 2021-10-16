<?php

namespace Database\Factories;

use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Str;

class MovieFactory extends Factory
{
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
    public function definition()
    {
        return [
            'name' => $this->faker->name(),
            'title' => $this->faker->word(),
            'director' => $this->faker->name(),
            'description' => $this->faker->sentence(),
            'year' => $this->faker->numberBetween(1910, 2021),
            'length' => Integer::random(10),
            'image' => Str::NULL,
            'ratings_enabled' => true,
            'created_at' => now(),
            'updated_at' => now(),
        ];
    }

}
