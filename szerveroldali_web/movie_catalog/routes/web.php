<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\MovieController;
use App\Http\Controllers\RatingController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return redirect()->route('movies.index');
});

Route::resource('movies', MovieController::class);
Route::get('toplist', [MovieController::class, 'toplist'])->withTrashed()->name('movies.toplist');
Route::post('/movies/save', [MovieController::class, 'save'])->withTrashed()->name('movies.save');
Route::post('/movies/destroy/{movie}', [MovieController::class, 'destroy'])->withTrashed()->name('movies.destroy'); 
Route::get('/movies/restore/{id}', [MovieController::class, 'restore'])->withTrashed()->name('movies.restore'); 


Route::resource('ratings', RatingController::class);
Route::get('/ratings/create/{movie_id}', [RatingController::class, 'create'])->name('ratings.create');
Route::post('/ratings/save/{movie_id}', [RatingController::class, 'save'])->name('ratings.save');
Route::get('/ratings/destroyAll/{id}',[RatingController::class, 'destroyAll'])->withTrashed()->name('ratings.destroyAll');


Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth'])->name('dashboard');

require __DIR__.'/auth.php';
