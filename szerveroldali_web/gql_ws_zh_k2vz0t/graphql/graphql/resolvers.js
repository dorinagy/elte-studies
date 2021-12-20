const { Recipe, Ingredient, Storage, Appliance } = require("../models");;
const auth = require("./auth");

module.exports = {
    Query: {
        helloAuth: auth((parent, params, context) => `Hello ${context.user.name}!`),
        recipes: async () => {
            const recipes = await Recipe.findAll({ include: [{ model: Ingredient }] })
            for (const recipe of recipes) {
                recipe.appliance = Appliance.findByPk(recipe.ApplianceId);
            }
            return recipes;

        },
        ingredient: async (_, { id }) => {
            if (!id) throw new Error("Id not provided");
            const ingredient = await Ingredient.findByPk(id);
            if (!ingredient) throw new Error("Ingredient not found");

            const storage = Storage.findByPk(ingredient.StorageId);

            const isInBigStorage = storage.capacity > 20;

            return {
                ...ingredient,
                isInBigStorage
            }
        },
        smallestStorage: async (_, { }) => {
            const storage = await Storage.findOne({ order: ["capacity"] });
            if (!storage) throw new Error("Storage not found");
            const ingredients = Ingredient.findAll({ where: { StorageId: storage.id } });

            return { ...storage, ingredients }
        },
        statistics: async (_, { }) => {
            const recipes = await Recipe.findAll({
                include: [{ model: Ingredient }],
            })
        
            let popularVegetarianRecipeCount = 0;
            let mostPopularRecipeName;
            let leastPopularRecipeName;
            let maxDoneCount = 0;
            let leastDoneCount = 100;
            let averageDoneCount = 0;
            let prevIdMost;
            let prevIdLeast;
            for (const recipe of recipes) {
                if (recipe.isVegetarian && recipe.doneCount > 10) popularVegetarianRecipeCount++;
                if (
                    recipe.doneCount > maxDoneCount || 
                    (recipe.doneCount == maxDoneCount && (!prevIdMost || recipe.id < prevIdMost))
                ) {
                    mostPopularRecipeName = recipe.name;
                    maxDoneCount = recipe.doneCount;
                    prevIdMost = recipe.id;
                }
                if (
                    recipe.doneCount < leastDoneCount || 
                    (recipe.doneCount == leastDoneCount && (!prevIdLeast || recipe.id < prevIdLeast))
                ) {
                    leastPopularRecipeName = recipe.name;
                    leastDoneCount = recipe.doneCount;
                    prevIdLeast = recipe.id;
                }

                averageDoneCount += recipe.doneCount;
            }
        
            return {
                popularVegetarianRecipeCount,
                mostPopularRecipeName,
                leastPopularRecipeName,
                averageDoneCount: (averageDoneCount / recipes.length).toFixed()
            };
        }
    },
    Mutation: {
        updateIngredient: async (_, { ingredientId, input }) => {
            const ingredient = await Ingredient.findByPk(ingredientId);
            if (!ingredient) throw new Error("Ingredient not found");
            await ingredient.update(input);
            return ingredient;
        },
        storeIngredients: async (_, { storageId, ingredients }) => {
            const storage = await Storage.findByPk(storageId);
            if (!storage) throw new Error("Storage not found");

            for (const ingredient of ingredients) {
                await Ingredient.create({...ingredient, StorageId: storageId});
            }
            return storage.getIngredients();
        },
        changeApplianceName: async (_, { applianceId, newName }) => {
            const recipesCount = await Recipe.findAll().length;
            const applianceCount = await Recipe.findAll({ where: { ApplianceId: applianceId } }).length;

            if (recipesCount*0,03 < applianceCount) {
                const appliance = await Appliance.findByPk(applianceId);
                await Appliance.update({ ...appliance, name: newName })
            }
            return Appliance.findByPk(applianceId);
        }
    },
};
