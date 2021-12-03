<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>
            @if(View::hasSection('title'))
                @yield('title')
            @else
                Laravel beadandó
            @endif
        </title>

        <!-- Styles -->
        <link rel="stylesheet" href="{{ asset('css/app.css') }}"/>
    </head>
    <body>
        <div id="app">
            @include('layouts.navigation')

            <main>
                @yield('main-content')
            </main>
        </div>
       
        <script src="{{ asset('js/app.js') }}"></script>
    </body>
</html>
