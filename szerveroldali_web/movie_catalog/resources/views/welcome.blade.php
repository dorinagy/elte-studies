<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Laravel kezdőcsomag</title>

        <!-- Styles -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.9/tailwind.min.css" integrity="sha512-SvJR34InARUfJb279ipE/gjQqX11MDKaly9MNb0vevuWQJmZ23UH7F/65ScEsvLI+myKGbdfA1En82wVSCHQ8A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="container mx-auto">
            <h1 class="text-4xl my-4">Szerveroldali webprogramozás</h1>
            <h2 class="text-3xl my-4">Laravel kezdőcsomag</h2>

            <ul class="list-disc ml-8">
                <li><span>Tantárgyi jegyzet Laravelhez: </span> <a class="no-underline hover:underline text-indigo-600" href="https://github.com/totadavid95/szerveroldali-21-osz#laravel">https://github.com/totadavid95/szerveroldali-21-osz#laravel</a></li>
                <li><span>Laravel dokumentációk: </span></li>
                <ul class="list-disc ml-8">
                    <li><a class="no-underline hover:underline text-indigo-600" href="https://laravel.com/docs">https://laravel.com/docs</a></li>
                    <li>API referencia: <a class="no-underline hover:underline text-indigo-600" href="https://laravel.com/api/8.x/index.html">https://laravel.com/api/8.x/index.html</a></li>
                </ul>
                <li><span>Tailwind dokumentációja: </span> <a class="no-underline hover:underline text-indigo-600" href="https://tailwindcss.com/docs">https://tailwindcss.com/docs</a></li>
            </ul>

            <div class="mt-8">
                <p>Jelen alkalmazás adatai:</p>
                <ul class="list-disc ml-8">
                    <li>Laravel verziója: {{ Illuminate\Foundation\Application::VERSION }}</li>
                    <li>Laravelt futtató PHP verziója: {{ PHP_VERSION }}</li>
                </ul>
            </div>
        </div>

    </body>
</html>
