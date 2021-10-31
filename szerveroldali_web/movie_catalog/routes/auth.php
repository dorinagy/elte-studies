<?php

use Illuminate\Support\Facades\Route;

use App\Http\Controllers\Auth\UserController;
use App\Http\Controllers\Auth\SessionController;

use App\Http\Controllers\Auth\VerifyEmailController;
use App\Http\Controllers\Auth\EmailVerificationController;
use App\Http\Controllers\Auth\VerificationNotificationController;

use App\Http\Controllers\Auth\NewPasswordController;
use App\Http\Controllers\Auth\ResetPasswordController;
use App\Http\Controllers\Auth\ConfirmPasswordController;

// register
Route::post('/register', [UserController::class, 'save'])
    ->middleware('guest');

Route::get('/register', [UserController::class, 'display'])
    ->middleware('guest')
    ->name('register');

// login
Route::post('/login', [SessionController::class, 'save'])
    ->middleware('guest');

Route::get('/login', [SessionController::class, 'display'])
    ->middleware('guest')
    ->name('login');

// confirm password
Route::get('/confirm-password', [ConfirmPasswordController::class, 'display'])
    ->middleware('auth')
    ->name('password.confirm');

Route::post('/confirm-password', [ConfirmPasswordController::class, 'save'])
    ->middleware('auth');

// verify email
Route::get('/verify-email', [EmailVerificationController::class, 'display'])
    ->middleware('auth')
    ->name('verification.notice');

Route::get('/verify-email/{id}/{hash}', [VerifyEmailController::class, 'invoke'])
    ->middleware(['auth', 'signed', 'throttle:6,1'])
    ->name('verification.verify');

// send email notification
Route::post('/email/verification-notification', [VerificationNotificationController::class, 'save'])
    ->middleware(['auth', 'throttle:6,1'])
    ->name('verification.send');

// logout
Route::post('/logout', [SessionController::class, 'destroy'])
    ->middleware('auth')
    ->name('logout');

// send reset password link
Route::get('/reset-password', [ResetPasswordController::class, 'display'])
    ->middleware('guest')
    ->name('password.request');

Route::post('/reset-password', [ResetPasswordController::class, 'save'])
    ->middleware('guest')
    ->name('password.email');

// set new password
Route::get('/new-password/{token}', [NewPasswordController::class, 'display'])
    ->middleware('guest')
    ->name('password.reset');

Route::post('/new-password', [NewPasswordController::class, 'save'])
    ->middleware('guest')
    ->name('password.update');
