const express = require("express");
const router = express.Router();

const models = require("../models");
const { Ingredient } = models;

const { ValidationError } = require("sequelize");

router.get("/", async (req, res) => {
    const ingredients = await Ingredient.findAll();
    res.send(ingredients);
});

router.get("/:id", async (req, res) => {
    const ingredient = await Ingredient.findByPk(req.params.id);
    if (!ingredient) return res.sendStatus(404);
    return res.send(ingredient);
});

router.put("/:id", async (req, res) => {
    let ingredient = await Ingredient.findByPk(req.params.id);
    
    if (!ingredient) return res.sendStatus(404);

    await Ingredient.update(req.body, { where: { id: req.params.id } })

    ingredient = await Ingredient.findByPk(req.params.id);

    return res.send(ingredient);
});

router.post("/", async (req, res) => {
    try {
        const ingredient = await Ingredient.create(req.body);
        
        return res.status(201).send(ingredient);
    } catch (e) {
        if (e instanceof ValidationError) {
            return res.sendStatus(400);
        }
        throw e;
    }
});

module.exports = router;
