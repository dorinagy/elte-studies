'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Appliance extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      this.hasMany(models.Recipe);
    }
  };
  Appliance.init({
    name: DataTypes.STRING
  }, {
    sequelize,
    modelName: 'Appliance',
  });
  return Appliance;
};
