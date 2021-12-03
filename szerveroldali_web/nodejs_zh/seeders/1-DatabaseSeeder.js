"use strict";

const faker = require("faker"); // http://marak.github.io/faker.js/faker.html
const colors = require("colors");
const models = require("../models");

const { Storage, Recipe, Ingredient, Appliance } = models;

module.exports = {
    up: async (queryInterface, Sequelize) => {
        try {
            const storages = [];
            const storagesCount = faker.datatype.number({ min: 5, max: 10 });
            for (let i = 0; i < storagesCount; i++) {
                storages.push(
                    await Storage.create({
                        name: faker.unique(faker.lorem.word),
                        capacity: faker.datatype.number(),
                    })
                );
            }

            const ingredients = [];
            const ingredientsCount = faker.datatype.number({ min: 5, max: 10 });
            for (let i = 0; i < ingredientsCount; i++) {
                ingredients.push(
                    await Ingredient.create({
                        name: faker.unique(faker.lorem.word),
                        amount: faker.datatype.number(),
                        StorageId: faker.random.arrayElement(storages).id,
                    })
                );
            }

            const appliances = [];
            const appliancesCount = faker.datatype.number({ min: 5, max: 10 });
            for (let i = 0; i < appliancesCount; i++) {
                appliances.push(
                    await Appliance.create({
                        name: faker.lorem.word(),
                    })
                );
            }

            const recipesCount = faker.datatype.number({ min: 5, max: 10 });
            for (let i = 0; i < recipesCount; i++) {
                const recipe = await Recipe.create({
                    name: faker.lorem.words(faker.datatype.number({ min: 1, max: 6 })),
                    isVegetarian: faker.datatype.boolean(),
                    doneCount: faker.datatype.number(),
                    ApplianceId: faker.random.arrayElement(appliances).id,
                });
                await recipe.setIngredients(faker.random.arrayElements(ingredients));
            }

            console.log("A DatabaseSeeder lefutott".green);
        } catch (e) {
            console.log("A DatabaseSeeder nem futott le teljesen, mivel az alábbi hiba történt:".red);
            console.log(colors.gray(e));
        }
    },

    down: async (queryInterface, Sequelize) => {
        // await queryInterface.bulkDelete('table_name', null, {});
    },
};
