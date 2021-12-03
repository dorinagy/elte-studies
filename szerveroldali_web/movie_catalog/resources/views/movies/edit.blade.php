@extends('layouts.base')

@section('title', 'Filmkatalógus új film')

@section('main-content')

<div class="container rounded">
    <form method="post" action="{{route('movies.update', $movie)}}" enctype="multipart/form-data">
        @method('PUT')
        @csrf

        <div class="form-group">

            <div class="p-2">
                <h1 class="font-extrabold uppercase">Film módosítása</h1>
            </div>

            <div class="items-center justify-center">
                <div class="mx-auto">
                    <label for="title" class="font-semibold p-2">Film címe</label>
                    <input class="form-control" type="text" name="title" id="title" value="{{$movie->title}}" />
                    @error('title')
                        <p class="text-red-600">{{$message}}</p>
                    @enderror
                </div>

                <div class="mx-auto">
                    <label for="director" class="font-semibold p-2">Rendező</label>
                    <input class="form-control" type="text" name="director" id="director" value="{{$movie->director}}" />
                    @error('director')
                        <p class="text-red-600">{{$message}}</p>
                    @enderror
                </div>

                <div class="mx-auto">
                    <label for="year" class="font-semibold p-2">Év</label>
                    <input class="form-control" type="number" name="year" id="year" value="{{$movie->year}}" />
                    @error('year')
                        <p class="text-red-600">{{$message}}</p>
                    @enderror
                </div>

                <div class="mx-auto">
                    <label for="description" class="font-semibold p-2">Film leírása</label>
                    <textarea class="form-control" name="description" id="description" maxlength="512">{{$movie->description}}</textarea>
                @error('description')
                    <p class="text-red-600">{{ $message }}</p>
                @enderror
                </div>

                <div class="mx-auto">
                    <label for="length" class="font-semibold p-2">Film hossza (seconds)</label>
                    <input class="form-control" type="number" name="length" id="length" value="{{$movie->length}}" />
                    @error('length')
                        <p class="text-red-600">{{ $message }}</p>
                    @enderror
                </div>
            </div>

            <div class="mx-auto">
                    <label for="image" class="font-semibold p-2">Film képe</label>
                    <input type="file" class="form-control-file" id="image">
                    @error('image')
                        <p class="text-red-500">{{ $message }}</p>
                    @enderror
            </div>

            @if ($movie->image)
                <div class="mx-auto">
                    <div class="flex justify-items-center">
                        <label for="image" class="p-2">Jelenlegi kép</label>
                    </div>
                    <img src="{{asset($movie->image ? 'images/'. $movie->image  : 'images/movie.png') }}" alt="{{$movie->title}}" srcset="">
                </div>
                <div class="mx-auto">
                <input id="delete_image" name="delete_image" type="checkbox" class="p-2"> Kép törlése</input>
                </div>
                
            @else
                <div class="mx-auto">
                    <p class="p-2">Jelenleg nincs kép beállítva a filmhez.</p>
                </div>
            @endif

            <div class="mx-auto">
                <button type="submit" class="btn btn-primary mt-3">Film módosítása</button>
            </div>
        </div>
    </form>
</div>

@endsection
