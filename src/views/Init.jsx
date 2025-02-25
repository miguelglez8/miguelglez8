import LinkedInIcon from "../icons/LinkedInIcon.jsx";
import GitHubIcon from "../icons/GitHubIcon.jsx";

const Init = () => (
    <section
        style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            minHeight: "100vh",
            backgroundColor: "#f5f5f5",
            padding: "20px",
        }}
    >
        <div style={{ flexShrink: 0, marginRight: "30px" }}>
            <img
                src="/images/foto.jpg"
                alt="Foto de Miguel González Navarro"
                style={{
                    width: "250px",
                    height: "250px",
                    borderRadius: "50%",
                    objectFit: "cover",
                    border: "5px solid #ddd",
                }}
            />
        </div>

        <div
            style={{
                backgroundColor: "#fff",
                padding: "30px",
                borderRadius: "10px",
                boxShadow: "0 6px 12px rgba(0, 0, 0, 0.1)",
                width: "50%",
                minHeight: "60vh",
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
            }}
        >
            <h1 style={{ fontSize: "28px", fontWeight: "bold", marginBottom: "10px" }}>
                Miguel González Navarro
            </h1>
            <h2 style={{ fontSize: "20px", color: "#555", marginBottom: "15px" }}>
                Ingeniero de Software
            </h2>
            <p style={{ fontSize: "18px", color: "#777", marginBottom: "25px" }}>
                📍 Moreda de Aller, Asturias (España)
            </p>

            <div style={{ display: "flex", gap: "20px", marginBottom: "15px" }}>
                <a
                    href="https://www.linkedin.com/in/miguelgonzaleznavarro"
                    style={{ marginLeft: "5px" }}
                >
                    <LinkedInIcon size={50} color="#0077B5" />
                </a>
                <a href="https://github.com/miguelglez8" style={{ marginLeft: "5px" }}>
                    <GitHubIcon size={50} color="#000000" />
                </a>
            </div>

            <div style={{ marginBottom: "20px" }}>
                <h3 style={{ fontSize: "20px", fontWeight: "bold", marginBottom: "8px" }}>
                    Idiomas
                </h3>
                <ul style={{ listStyle: "none", padding: 0, margin: 0 }}>
                    <li style={{ marginBottom: "8px", fontSize: "18px" }}>🇪🇸 Español (Nativo)</li>
                    <li style={{ fontSize: "18px" }}>🇬🇧 Inglés (Nivel intermedio)</li>
                </ul>
            </div>

            <div>
                <h3 style={{ fontSize: "20px", fontWeight: "bold", marginBottom: "8px" }}>
                    Principales Aptitudes
                </h3>
                <ul style={{ listStyle: "none", padding: 0, margin: 0 }}>
                    <li style={{ marginBottom: "8px", fontSize: "18px" }}>💻 Desarrollo de software</li>
                    <li style={{ marginBottom: "8px", fontSize: "18px" }}>🤝 Trabajo en equipo</li>
                    <li style={{ marginBottom: "8px", fontSize: "18px" }}>☕ Java</li>
                    <li style={{ fontSize: "18px" }}>🔧 Git</li>
                </ul>
            </div>
        </div>
    </section>
);

export default Init;
