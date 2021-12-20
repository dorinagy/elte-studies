'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Recipe extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      this.belongsToMany(models.Ingredient, { through: 'Recipes_Ingredients' });
      this.belongsTo(models.Appliance);
    }
  };
  Recipe.init({
    name: {
      type: DataTypes.STRING,
      unique: true
    },
    isVegetarian: DataTypes.BOOLEAN,
    doneCount: DataTypes.INTEGER,
    ApplianceId: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'Recipe',
  });
  return Recipe;
};
