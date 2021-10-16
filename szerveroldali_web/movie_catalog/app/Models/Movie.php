<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

use \App\Models\Rating;

class Movie extends Model
{
    use HasFactory;
    use SoftDeletes;

    protected $fillable = ['title', 'director', 'description', 'year', 'length', 'image'];

    public function ratings() {
        return $this->belongsToMany(Rating::class);
    }

}