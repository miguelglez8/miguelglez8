import "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./styles.css";
import NavBar from "./views/NavBar.jsx";
import About from "./views/About.jsx";
import Init from "./views/Init.jsx";
import Portfolio from "./views/Portfolio.jsx";
import Contact from "./views/Contact.jsx";
import Footer from "./views/Footer.jsx";
import { ABOUT_PATH, BASE_PATH, CONTACT_PATH, PORTFOLIO_PATH } from "./routes/routes.js";

const App = () => {
    return (
        <Router>
            <div className="app-container">
                <NavBar />
                <Routes>
                    <Route path={BASE_PATH} element={<Init />} />
                    <Route path={ABOUT_PATH} element={<About />} />
                    <Route path={PORTFOLIO_PATH} element={<Portfolio />} />
                    <Route path={CONTACT_PATH} element={<Contact />} />
                </Routes>
                <Footer />
            </div>
        </Router>
    );
};

export default App;
