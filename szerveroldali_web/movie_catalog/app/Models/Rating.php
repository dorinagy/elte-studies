<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

use \App\Models\User;
use \App\Models\Movie;

class Rating extends Model
{
    use HasFactory;
    use SoftDeletes;

    protected $fillable = ['user_id', 'movie_id', 'rating', 'comment',];

    public function user() {
        return $this->belongsTo(User::class);
    }

    public function movie() {
        return $this->belongsTo(Movie::class);
    }

}