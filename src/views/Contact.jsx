import { Mail, Github, Linkedin, MapPin } from "lucide-react";

const Contact = () => (
    <section
        style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            minHeight: "70vh",
            backgroundColor: "#f5f5f5",
        }}
    >
        <div
            style={{
                textAlign: "center",
                padding: "30px",
                backgroundColor: "#fff",
                boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                borderRadius: "10px",
                width: "350px",
            }}
        >
            <h2 style={{ fontSize: "24px", fontWeight: "bold", marginBottom: "20px" }}>
                ¡Contáctame!
            </h2>

            <p style={{ display: "flex", alignItems: "center", justifyContent: "center", gap: "10px", fontSize: "18px", marginBottom: "15px" }}>
                <Mail color="#3b82f6" size={22} />
                <a href="mailto:miguemoreda@gmail.com" style={{ color: "#2563eb", textDecoration: "none" }}>
                    miguemoreda@gmail.com
                </a>
            </p>

            <p style={{ display: "flex", alignItems: "center", justifyContent: "center", gap: "10px", fontSize: "18px", marginBottom: "15px" }}>
                <a href="https://www.linkedin.com/in/miguelgonzaleznavarro" style={{ display: "flex", alignItems: "center", gap: "8px", color: "#0077B5", textDecoration: "none" }}>
                    <Linkedin size={24} />
                    miguelgonzaleznavarro
                </a>
            </p>

            <p style={{ display: "flex", alignItems: "center", justifyContent: "center", gap: "10px", fontSize: "18px", marginBottom: "15px" }}>
                <a href="https://github.com/miguelglez8" style={{ display: "flex", alignItems: "center", gap: "8px", color: "#333", textDecoration: "none" }}>
                    <Github size={24} />
                    miguelglez8
                </a>
            </p>

            <p style={{ display: "flex", alignItems: "center", justifyContent: "center", gap: "8px", fontSize: "18px", color: "#444" }}>
                <MapPin color="#dc2626" size={22} />
                Moreda de Aller, Asturias
            </p>
        </div>
    </section>
);

export default Contact;