import "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./styles.css";
import NavBar from "./components/NavBar.jsx";
import About from "./views/About.jsx";
import Init from "./views/Init.jsx";
import Projects from "./views/Projects.jsx";
import Contact from "./views/Contact.jsx";
import Footer from "./components/Footer.jsx";
import LanguageSelector from "./components/LanguageSelector.jsx";
import { ABOUT_PATH, BASE_PATH, CONTACT_PATH, PROJECTS_PATH } from "./routes/routes.js";
import "./i18n";

const App = () => {
    return (
        <Router>
            <div className="app-container">
                <NavBar/>
                <LanguageSelector/>
                <Routes>
                    <Route path={BASE_PATH + "/"} element={<Init/>}/>
                    <Route path={ABOUT_PATH} element={<About/>}/>
                    <Route path={PROJECTS_PATH} element={<Projects/>}/>
                    <Route path={CONTACT_PATH} element={<Contact/>}/>
                </Routes>
                <Footer/>
            </div>
        </Router>
    );
};

export default App;
