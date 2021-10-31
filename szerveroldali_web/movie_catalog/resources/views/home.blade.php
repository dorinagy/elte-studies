@extends('layouts.base')

@section('title', 'Filmkatalógus kezdőlap')

@section('main-content')
    <div class="container">

        <h1>{{ $number }}</h1>
        <h1>{{ $name }}</h1>

        <div class="row justify-content-between">
            <div class="col-12 col-md-8">
                <h1>Üdvözlünk!</h1>
                <h3 class="mb-1">Elérhető filmek</h3>
            </div>
           <!--  <div class="col-12 col-md-4">
                <div class="py-md-3 text-md-right">
                    <p class="my-1">Elérhető műveletek:</p>
                    <a href="new-post.html" role="button" class="btn btn-sm btn-success mb-1"><i class="fas fa-plus-circle"></i> Új bejegyzés</a>
                    <a href="new-category.html" role="button" class="btn btn-sm btn-success mb-1"><i class="fas fa-plus-circle"></i> Új kategória</a>
                </div>
            </div> -->
        </div>

        <div class="row mt-3">
            <div class="col-12 col-lg-9">
                <div class="row">
                        <div class="col-12 col-md-6 col-lg-4 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="mb-2">
                                        <h5 class="card-title mb-0">cím</h5>
                                        <small class="text-secondary">
                                            <span class="mr-2">
                                                <i class="fas fa-user"></i>
                                                <span>Dávid</span>
                                            </span>
                                            <span class="mr-2">
                                                <i class="far fa-calendar-alt"></i>
                                                <span>létrehozás dátuma</span>
                                            </span>
                                        </small>
                                    </div>
                                    <p class="card-text">A bejegyzés szövegének az eleje...</p>
                                    <a href="post.html" class="btn btn-primary">Megtekint <i class="fas fa-angle-right"></i></a>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
        </div>


                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Előző</a>
                        </li>
                        <li class="page-item"><a class="page-link" href="?page=1">1</a></li>
                        <li class="page-item"><a class="page-link" href="?page=2">2</a></li>
                        <li class="page-item"><a class="page-link" href="?page=3">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#">Következő</a>
                        </li>
                    </ul>
                </nav>

            </div>
            <div class="col-12 col-lg-3">
                <div class="row">
                    <div class="col-12 mb-3">
                        <div class="card bg-light">
                            <div class="card-body">
                                <h5 class="card-title mb-2">Keresés</h5>
                                <p class="small">Bejegyzés keresése cím alapján.</p>
                                <form>
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="Keresett cím">
                                    </div>
                                    <button type="submit" class="btn btn-primary"><i class="fas fa-search"></i> Keresés</button>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 mb-3">
                        <div class="card bg-light">
                            <div class="card-body">
                                <h5 class="card-title mb-2">Kategóriák</h5>
                                <p class="small">Bejegyzések megtekintése egy adott kategóriához.</p>
                                <a href="#" class="badge badge-primary">Primary</a>
                                <a href="#" class="badge badge-secondary">Secondary</a>
                                <a href="#" class="badge badge-success">Success</a>
                                <a href="#" class="badge badge-danger">Danger</a>
                                <a href="#" class="badge badge-warning">Warning</a>
                                <a href="#" class="badge badge-info">Info</a>
                                <a href="#" class="badge badge-light">Light</a>
                                <a href="#" class="badge badge-dark">Dark</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 mb-3">
                        <div class="card bg-light">
                            <div class="card-body">
                                <h5 class="card-title mb-2">Statisztika</h5>
                                <div class="small">
                                    <p class="mb-1">Adatbázis statisztika:</p>
                                    <ul class="fa-ul">
                                        <li><span class="fa-li"><i class="fas fa-user"></i></span>Felhasználók: felhasználók száma</li>
                                        <li><span class="fa-li"><i class="fas fa-file-alt"></i></span>Bejegyzések: 1
                                        </li>
                                        <li><span class="fa-li"><i class="fas fa-comments"></i></span>Hozzászólások: 3
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <hr>
        <footer>
            <div class="container">
                <div class="d-flex flex-column align-items-center">
                    <div>
                        <span class="small">Alapszintű Blog</span>
                        <span class="mx-1">·</span>
                        <span class="small">Laravel 8.2</span>
                        <span class="mx-1">·</span>
                        <span class="small">PHP 8.0.2</span>
                    </div>

                    <div>
                        <span class="small">Szerveroldali webprogramozás 2020-21-2</span>
                    </div>
                </div>
            </div>
        </footer>
    </div>
@endsection
