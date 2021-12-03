@extends('layouts.base')

@section('title', 'Filmkatalógus adatlap')

@section('main-content')

    @if (Session::has('movie_deleted'))
        @if (Session::get('movie_deleted'))
            <div class="alert alert-success">
                <div class="font-semibold">
                    A film sikeresen törölve!
                </div>
            </div>
        @else
            <div class="alert alert-danger">
                <div class="font-semibold">
                    A film törlése sikertelen volt!
                </div>
            </div>
        @endif
    @endif

    @if (Session::has('movie_restored'))
        @if (Session::get('movie_restored'))
            <div class="alert alert-success">
                <div class="font-semibold">
                    A film helyreállíása sikeres volt!
                </div>
            </div>
        @else
            <div class="alert alert-danger">
                <div class="font-semibold">
                    Nem sikerült a film helyreállítása!
                </div>
            </div>
        @endif
    @endif
    @if (Session::has('all_ratings_deleted'))
        @if (Session::get('all_ratings_deleted'))
            <div class="alert alert-success">
                <div class="font-semibold">
                    Minden értékelés törölve lett!
                </div>
            </div>
        @else
            <div class="alert alert-danger">
                <div class="font-semibold">
                    Nem sikerült az értékelések törlése!
                </div>
            </div>
        @endif
    @endif

    @if (Session::has('movie_created'))
        @if (Session::get('movie_created'))
            <div class="alert alert-success">
                <div class="font-semibold">
                    A film sikeresen létrehozva!
                </div>
            </div>
        @else
            <div class="alert alert-danger">
                <div class="font-semibold">
                    A film létrehozása sikertelen volt!
                </div>
            </div>
        @endif
    @endif

    @if (Session::has('rating_created'))
        <div class="alert alert-success">
            <div class="font-semibold">
                Az értékelés sikeresen létrehozva!
            </div>
        </div>
    @endif

    @if (Session::has('movie_edited'))
        @if (Session::get('movie_edited'))
            <div class="alert alert-success">
                <div class="font-semibold">
                    A film sikeresen módosítva!
                </div>
            </div>
        @else
            <div class="alert alert-danger">
                <div class="font-semibold">
                    A film módosítása sikertelen volt!
                </div>
            </div>
        @endif
    @endif

    <div class="container">
        <div class="flex">
            <h2 class="mx-auto p-3 t-3 font-semibold">{{$movie->title}}</h2>

            <div class="d-flex">
                <img src="{{asset($movie->image ? 'images/'. $movie->image  : 'images/movie.png') }}" alt="{{$movie->title}}">

                <div class="text-sm">
                    <div class="p-3"> <i class="fas fa-star"></i>értékelés: {{$movie->ratings->avg('rating');}}/5</div>
                    <div class="p-3">év: {{$movie->year}}</div>
                    <div class="p-3">hossz: {{gmdate("H:i:s", $movie->length)}}</div>
                </div>
            </div>

            <div class="relative">
                
                <div class="p-3">
                    <h3>Leírás:</h3>
                    <p>{{$movie->description}}</p>
                </div>
                <div class="font-bold p-3">
                    <p>Rendező: {{$movie->director}}</p>
                </div>
            </div>
            @auth
                @if (Auth::user()->is_admin)
                <div class="d-flex justify-content-end flex-row p-3">
                    <a href="{{route('movies.edit', $movie)}}" class="rounded p-2">
                        <i class="btn btn-info">Film módosítása</i>
                    </a>
                    @if (!$movie->trashed())
                        <form method="post" action="{{ route('movies.destroy', $movie)}}" id="movie-delete" class="p-2">
                            @csrf
                            <a href="#" onclick="event.preventDefault(); document.querySelector('#movie-delete').submit();" class="rounded">
                                <i class="btn btn-info">Film törlése</i>
                            </a>
                        </form>
                    @else
                        <a href="{{route('movies.restore', $movie)}}" class="rounded p-2">
                            <i class="btn btn-info">Film helyreállítása</i> 
                        </a>
                    @endif
                        <a href="{{route('ratings.destroyAll', $movie->id)}}" class="rounded p-2">
                            <i class="btn btn-info">Értékelések törlése</i> 
                        </a>
                </div>
                @endif
            @endauth
        </div>
    </div>

    <div class="container justify-center">

        @auth
            @if (! $movie->ratings_enabled)
                <p class="font-bold">Az értékelések nem engedélyezettek ennél a filmnél.</p>
            @endif
        @endauth

        <div class="p-2">
            <h3>Értékelések:</h3>
        </div>
        @forelse ($movie->ratings->sortBy('created_at') as $rating)
            <div class="border p-2">
                <span class="font-semibold">
                    {{$users->find($rating->user_id)->name}}
                </span>
                <div>
                    <span class="text-sm"> <i class="fas fa-star"></i> {{$rating->rating}}/5</span>
                </div>
                <p>{{$rating->comment}}</p>
                <div>
                    <span> {{$rating->updated_at}} </span>
                </div>
            </div>
        @empty
            <p class="p-2">Még nincsenek értékelések.</p>
        @endforelse

        <div class="d-flex justify-content-end">
        @auth
            @if (Auth::user()->is_admin || ! $movie->ratings_enabled)
            <div class="p-2">
                <a href="{{route('ratings.create', $movie->id)}}" class="btn btn-primary">Új értékelés<i class="fas fa-angle-right"></i></a>
            </div>
            @endif
        @endauth
        </div>
    </div>

@endsection
