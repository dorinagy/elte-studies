@extends('layouts.base')

@section('title', 'Filmkatalógus kezdőlap')

@section('main-content')
    <div class="container">
        <div class="row justify-content-between">
            <div class="col-12 col-md-8">
                <h3 class="mx-auto p-3 t-3 mb-1">Elérhető filmek</h3>
            </div>
        </div>

        <div cclass="col-12 col-md-6 col-lg-4 mb-3">
        @forelse ($movies as $movie)
            @if ($movie->deleted_at === null || (Auth::check() && Auth::user()->is_admin))
                <div class="card {{$movie->deleted_at ? 'opacity-50' : ''}}">
                    <div class="card-body">
                        <div class="mb-2">
                            <h5 class="card-title mb-0">
                                <a href="{{route('movies.show', $movie)}}">
                                    {{$movie->title}}
                                </a>
                            </h5>
                            <small class="text-secondary">
                                <span class="mr-2">
                                    @if ($movie->ratings->avg('rating') == null)
                                        <i class="fas fa-star text-gray-500 hover:text-black">not rated</i>
                                    @else
                                        <i class="fas fa-star text-yellow-500"></i> {{$movie->ratings->avg('rating')}}/5
                                    @endif                                        
                                </span>
                                <span class="mr-2">
                                    <i class="far fa-calendar-alt"></i>
                                    <span>{{$movie->year}}</span>
                                </span>
                                <span class="mr-2">
                                    <i class="far fa-calendar-alt"></i>
                                    <span>{{gmdate("H:i:s", $movie->length)}}</span>
                                </span>
                            </small>
                        </div>
                        <div class="mb-2">
                        <img src="{{asset($movie->image ? 'images/'. $movie->image  : 'images/movie.png') }}" width="160" height="140" alt="{{$movie->title}}"
                             class="{{$movie->deleted_at ? 'opacity-50' : ''}}">
                        </div>
                        <p class="card-text">{{$movie->description}}</p>
                        <a href="{{route('movies.show', $movie)}}" class="btn btn-primary">Megtekint <i class="fas fa-angle-right"></i></a>
                    </div>
                </div>
            @endif
        @empty
            <p>Nincsenek filmek az adatbázisban.</p>
        @endforelse

    </div>
    <div class="d-flex">
        <!--  pagination   -->
        <div class="mr-auto p-2">
            <div class="pagination">{{ $movies->links() }}</div>
        </div>
        @auth
            @if (Auth::user()->is_admin)
            <div class="p-2">
                <a href="{{route('movies.create')}}" class="btn btn-primary">Új film <i class="fas fa-angle-right"></i></a>
            </div>
            @endif
        @endauth
   </div>
    
@endsection
