const express = require("express");
const router = express.Router();

const jwt = require('jsonwebtoken');

const EMAIL = 'user@szerveroldali.hu';

router.get("/", async (req, res) => {
    const { token } = req.params
    jwt.verify(token, process.env.JWT_SECRET, (err, verifiedJwt) => {
        if (err){
            return res.status(401)
        } else {
            return res.status(200)
        }
    })
})
router.post("/", async (req, res) => {
    const param = req.body.email;

    if (param !== EMAIL) res.send(401);

    const token = jwt.sign({ email: EMAIL }, process.env.JWT_SECRET, {
        expiresIn: '1h'
    });
    
    res.send({ accessToken: token });
});

module.exports = router;