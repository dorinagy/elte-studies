@extends('layouts.base')

@section('title', 'Filmkatalógus - új értékelés')

@section('main-content')
      <div class="container p-2">
        <h1>Új értékelés</h1>
        <p class="font-semibold">Ezen az oldalon tudsz új értékelést létrehozni.</p>
        <div class="d-flex justify-content-end p-3">
            <div class="btn btn-info">
                <a href="{{route('movies.index')}}"><i class="fas fa-long-arrow-alt-left"></i>Vissza a filmekhez</a>
            </div>
        </div>
        <form action="{{route('ratings.save', $movie_id)}}" method="post" enctype="multipart/form-data">
        @method('POST')
        @csrf
            <div class="form-group row">
                <label for="rating" class="col-sm-2 col-form-label">Értékelés*</label>
                <div class="col-sm-10">
                    <input type="number" min="1" max="5" class="form-control" id="rating" name="rating">
                </div>
            </div>
            <div class="form-group row">
                <label for="comment" class="col-sm-2 col-form-label">Megjegyzés</label>
                <div class="col-sm-10">
                    <textarea id="comment" rows="5" class="form-control" name="text" placeholder="Megjegyzés"></textarea>
                </div>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Létrehoz</button>
            </div>
        </form>
    </div>
@endsection
