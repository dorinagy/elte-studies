const express = require("express");
const router = express.Router();

const models = require("../models");
const { Appliance } = models;

router.post("/changeName", async (req, res) => {
    
    if (req.body.oldName && req.body.newName) {
        await Appliance.update({ name: req.body.newName }, { where: { name: req.body.oldName } });
    }
    const appliances = await Appliance.findAll({ where: { name: req.body.newName }});
    res.send(appliances);
});

module.exports = router;
