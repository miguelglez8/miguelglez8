import { Mail, Github, Linkedin, MapPin } from "lucide-react";
import ContactItem from "../components/contact/ContactItem.jsx";
import cvData from "../data/cvData.js";

const Contact = () => (
    <section style={{ display: "flex", alignItems: "center", justifyContent: "center", minHeight: "70vh", backgroundColor: "#f5f5f5" }}>
        <div style={{ textAlign: "center", padding: "30px", backgroundColor: "#fff", boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)", borderRadius: "10px", width: "350px" }}>
            <h2 style={{ fontSize: "24px", fontWeight: "bold", marginBottom: "20px" }}>¡Contáctame!</h2>

            <ContactItem
                icon={Mail}
                link={"mailto:" + cvData.personal.mail}
                text={cvData.personal.mail}
                color="#2563eb"
            />
            <ContactItem
                icon={Linkedin}
                link={cvData.personal.linkedin}
                text={cvData.personal.nickname}
                color="#0077B5"
            />
            <ContactItem
                icon={Github}
                link={cvData.personal.github}
                text={cvData.personal.profile}
                color="#333"
            />
            <ContactItem
                icon={MapPin}
                text={cvData.personal.location}
                color="#dc2626"
            />
        </div>
    </section>
);

export default Contact;