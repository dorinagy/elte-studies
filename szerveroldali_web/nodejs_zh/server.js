require("dotenv").config();
require("express-async-errors");

const fs = require("fs").promises;
const express = require("express");
const date = require("date-and-time");
const AutoTester = require("./test/inject");
const { StatusCodes, ReasonPhrases } = require("http-status-codes");

const app = express();

app.use(express.json());

app.use("/auth", require("./routers/auth"));
app.use("/storages", require("./routers/storage"));
app.use("/ingredients", require("./routers/ingredient"));
app.use("/recipes", require("./routers/recipe"));
app.use("/appliances", require("./routers/appliance"));

app.use(async (err, req, res, next) => {

    if (res.headersSent) {
        return next(err);
    }
    await fs.appendFile(
        "error.log",
        [`[${date.format(new Date(), "YYYY. MM. DD. HH:mm:ss")}]`, err.stack].join("\n") + "\n\n"
    );
    return res.status(StatusCodes.INTERNAL_SERVER_ERROR).send({
        httpStatus: ReasonPhrases.INTERNAL_SERVER_ERROR,
        errorDetails: {
            name: err.name,
            message: err.message,
            stack: [...err.stack.split("\n")],
        },
    });
});


(async () => {
    const port = process.env.PORT || 4000;
    app.listen(port, () => {
        console.log(`Az Express app fut, ezen a porton: ${port}`);

        AutoTester.handleStart();
    });
})();
