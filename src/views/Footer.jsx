import { Link } from "react-router-dom";
import GitHubIcon from "../icons/GitHubIcon.jsx";
import cvData from '../data/cvData';

function Footer() {
    return (
        <footer className="footer">
            <p>
                &copy; {new Date().getFullYear()} {cvData.personal.name}. Todos los derechos reservados.
                <Link to={cvData.personal.github} style={{ marginLeft: '5px' }}>
                    <GitHubIcon size={20} color="#000000"/>
                </Link>
            </p>
        </footer>
    )
}

export default Footer;