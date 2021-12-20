"use strict";

// Faker dokumentáció: http://marak.github.io/faker.js/faker.html
const faker = require("faker");
const colors = require("colors");
const models = require("../models");
//const { ... } = models;

const STORAGE_COUNT = 10;
const APPLIANCE_COUNT = 5;
const INGREDIENT_COUNT = 15;
const RECIPE_COUNT = 4;

module.exports = {
    up: async (queryInterface, Sequelize) => {
        try {
            await queryInterface.bulkInsert(
                "Storages",
                Array.from({ length: STORAGE_COUNT }).map(() => ({
                    name: faker.unique(faker.lorem.word),
                    capacity: faker.datatype.number({ min: 1, max: 100 }),
                })),
                {}
            );

            await queryInterface.bulkInsert(
                "Appliances",
                Array.from({ length: APPLIANCE_COUNT }).map(() => ({
                    name: faker.lorem.word(),
                })),
                {}
            );

            await queryInterface.bulkInsert(
                "Ingredients",
                Array.from({ length: INGREDIENT_COUNT }).map(() => ({
                    name: faker.lorem.word(),
                    amount: faker.datatype.number({ min: 1, max: 100 }),
                    StorageId: faker.datatype.number({ min: 1, max: STORAGE_COUNT }),
                })),
                {}
            );

            await queryInterface.bulkInsert(
                "Recipes",
                Array.from({ length: INGREDIENT_COUNT }).map(() => ({
                    name: faker.unique(faker.lorem.word),
                    isVegetarian: faker.datatype.boolean(),
                    doneCount: faker.datatype.number({ min: 0, max: 100 }),
                    ApplianceId: faker.datatype.number({ min: 1, max: APPLIANCE_COUNT }),
                })),
                {}
            );

            await queryInterface.bulkInsert(
                "Recipes_Ingredients",
                Array.from({ length: RECIPE_COUNT }).map(() => ({
                    RecipeId: faker.datatype.number({ min: 1, max: RECIPE_COUNT }),
                    IngredientId: faker.datatype.number({ min: 1, max: INGREDIENT_COUNT }),
                })),
                {}
            );

            console.log("A DatabaseSeeder lefutott".green);
        } catch (e) {
            // Ha a seederben valamilyen hiba van, akkor alapértelmezés szerint elég szegényesen írja
            // ki azokat a rendszer a seeder futtatásakor. Ezért ez Neked egy segítség, hogy láthasd a
            // hiba részletes kiírását.
            // Így ha valamit elrontasz a seederben, azt könnyebben tudod debug-olni.
            console.log("A DatabaseSeeder nem futott le teljesen, mivel az alábbi hiba történt:".red);
            console.log(colors.gray(e));
        }
    },

    // Erre alapvetően nincs szükséged, mivel a parancsok úgy vannak felépítve,
    // hogy tiszta adatbázist generálnak
    down: async (queryInterface, Sequelize) => {},
};
