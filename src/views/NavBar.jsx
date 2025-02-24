import { Link } from "react-router-dom";
import { BASE_PATH, ABOUT_PATH, PROJECTS_PATH, CONTACT_PATH } from '../routes/routes.js';

const NavBar = () => (
    <nav className="navbar">
        <ul>
            <li><Link to={BASE_PATH}>Inicio</Link></li>
            <li><Link to={ABOUT_PATH}>Información</Link></li>
            <li><Link to={PROJECTS_PATH}>Proyectos</Link></li>
            <li><Link to={CONTACT_PATH}>Contacto</Link></li>
        </ul>
    </nav>
);

export default NavBar;