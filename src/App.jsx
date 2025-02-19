import "react";
import "./App.css";

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <h1>Mi Portfolio</h1>
                <section>
                    <h2>Sobre mí</h2>
                    <p>
                        Soy un desarrollador web con experiencia en React y tecnologías modernas. Este es mi portfolio basado en mi CV.
                    </p>
                </section>
                <section>
                    <h2>Experiencia</h2>
                    <ul>
                        <li>Desarrollador Frontend en Empresa X (2020-2023)</li>
                        <li>Desarrollador Backend en Empresa Y (2018-2020)</li>
                    </ul>
                </section>
                <section>
                    <h2>Habilidades</h2>
                    <ul>
                        <li>React</li>
                        <li>JavaScript</li>
                        <li>CSS, HTML</li>
                        <li>Node.js</li>
                        <li>Git</li>
                    </ul>
                </section>
                <footer>
                    <p>Contacto: tuemail@dominio.com</p>
                </footer>
            </header>
        </div>
    );
}

export default App;
