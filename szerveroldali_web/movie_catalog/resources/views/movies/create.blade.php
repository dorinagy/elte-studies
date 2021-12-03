@extends('layouts.base')

@section('title', 'Filmkatalógus új film')

@section('main-content')

<div class="container p-2">
    <h1>Film létrehozása</h1>
    <form method="post" action="{{route('movies.save')}}" enctype="multipart/form-data">
        @csrf
        <div class="">
            <div class="form-group row">
                <label for="title" class="font-semibold">Film címe</label>
                <input class="form-control" type="text" name="title" id="title" value="{{old('title')}}" />
                @error('title')
                    <p class="text-red-600">{{$message}}</p>
                @enderror
            </div>

            <div class="form-group row">
                <label for="director" class="font-semibold">Rendező</label>
                <input class="form-control" type="text" name="director" id="director" value="{{old('director')}}" />
                @error('director')
                    <p class="text-red-600">{{$message}}</p>
                @enderror
            </div>

            <div class="form-group row">
                <label for="year" class="font-semibold">Év</label>
                <input  class="form-control" type="number" name="year" id="year" value="{{old('year')}}" />
                @error('year')
                    <p class="text-red-600">{{$message}}</p>
                @enderror
            </div>

            <div class="form-group row">
                <label for="description" class="font-semibold">Film leírása</label>
                <textarea class="form-control" name="description" id="description" maxlength="512">{{old('description')}}</textarea>
            @error('description')
                <p class="text-red-600">{{ $message }}</p>
            @enderror
            </div>

            <div class="form-group row">
                <label for="length" class="font-semibold">Film hossza (seconds)</label>
                <input class="form-control" type="number" name="length" id="length" value="{{old('length')}}" />
                @error('length')
                    <p class="text-red-600">{{ $message }}</p>
                @enderror
            </div>

            <div class="form-group row">
                <label class="mr-3" for="image">Film képe</label>
                <input type="file" id="image" name="image">
                @error('image')
                    <p class="text-red-500">{{ $message }}</p>
                @enderror
            </div>

        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">Film létrehozása</button>
        </div>
    </form>
</div>

@endsection
