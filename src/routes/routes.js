import cvData from "../data/cvData.js";

const BASE_PATH = "/" + cvData.personal.profile;

const ABOUT_PATH = BASE_PATH + "/about";
const PROJECTS_PATH = BASE_PATH + "/projects";
const CONTACT_PATH = BASE_PATH + "/contact";

export {
    BASE_PATH,
    ABOUT_PATH,
    PROJECTS_PATH,
    CONTACT_PATH
};