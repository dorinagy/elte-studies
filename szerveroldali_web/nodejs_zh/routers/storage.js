const express = require("express");
const router = express.Router();

const models = require("../models");
const { Storage, Ingredient } = models;

const jwtMiddleware = require('express-jwt');
const { ValidationError } = require("sequelize");

router.get("/", async (req, res) => {
    const storages = await Storage.findAll();
    res.send(storages);
});

router.get("/:id", async (req, res) => {
    const storage = await Storage.findByPk(req.params.id);
    if (!storage) return res.sendStatus(404);
    return res.send(storage);
});

router.post("/", async (req, res) => {
    try {
        const storage = await Storage.create(req.body);

        return res.status(201).send(storage);
    } catch (e) {
        if (e instanceof ValidationError) {
            return res.sendStatus(400);
        }
        throw e;
    }
});

router.get("/:id/clean", jwtMiddleware({ secret: process.env.JWT_SECRET, algorithms: [process.env.JWT_ALGO] }), async (err, req, res) => {

    if (err) res.status(401);

    const storage = await Storage.findByPk(req.params.id);

    if (!storage) res.status(404)

    let sum = 0;
    for (const ingredient of storage.Ingredients) {
        sum += ingredient.amount;
    }

    let removedIngredientCount = 0;

    if (storage.capacity >= sum) {
        res.send({ removedIngredientCount })
    } 

    let substract = sum - storage.capacity;

    const ingredient = storage.Ingredients[0];

    await Ingredient.update({ amount: amount - substract }, { where: { id: ingredient.id } });
    
    removedIngredientCount = substract;

    res.send({ removedIngredientCount })
});



module.exports = router;