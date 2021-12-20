'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Storage extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      this.hasMany(models.Ingredient);
    }
  };
  Storage.init({
    name: {
      type: DataTypes.STRING,
      unique: true
    },
    capacity: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'Storage',
  });
  return Storage;
};
