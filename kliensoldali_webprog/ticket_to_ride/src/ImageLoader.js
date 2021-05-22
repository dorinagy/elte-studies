import white from "./res/w_card_front.jpg";
import yellow from "./res/y_card_front.jpg";
import orange from "./res/o_card_front.jpg";
import red from "./res/r_card_front.jpg";
import green from "./res/g_card_front.jpg";
import blue from "./res/b_card_front.jpg";
import purple from "./res/p_card_front.jpg";
import black from "./res/bl_card_front.jpg";
import card_back from "./res/card_back.png";
import train_ticket_back from "./res/train_ticket_back.png";
import colorful from "./res/colorful.jpeg";


const images = {
    white,
    yellow,
    orange,
    red,
    green,
    blue,
    purple,
    black,
    card_back,
    train_ticket_back,
    colorful
};

export const getSource = (name) => {
    return images[name];
};
