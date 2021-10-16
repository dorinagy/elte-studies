:: Ezzel a batch scripttel lehet nullarol inicializalni a Laravel projektet Windows rendszereken
copy .env.example .env
call php artisan key:generate
call composer install --no-interaction
call npm install
call npm run prod
:: Egy ures sqlite fajlnak leteznie kell, kulonben a migrate nem mukodik
type nul > database/database.sqlite
call php artisan migrate:fresh
call php artisan db:seed
echo "Az inicializalo parancsok lefutottak, ha minden rendben ment, akkor indithato a projekt..."
@pause
