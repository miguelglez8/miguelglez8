import { Link } from "react-router-dom";
import GitHubIcon from "../icons/GitHubIcon.jsx";

function Footer() {
    return (
        <footer className="footer">
            <p>
                &copy; {new Date().getFullYear()} Miguel González Navarro. Todos los derechos reservados.
                <Link to="https://github.com/miguelglez8/miguelglez8" style={{ marginLeft: '5px' }}>
                    <GitHubIcon size={20} color="#000000"/>
                </Link>
            </p>
        </footer>
    )
}

export default Footer;