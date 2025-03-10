import { Mail, Github, Linkedin, MapPin } from "lucide-react";
import { useTranslation } from "react-i18next";
import ContactItem from "../components/contact/ContactItem.jsx";
import cv from "../data/cv.js";

const Contact = () => {
    const { t } = useTranslation();

    return (
        <section style={{ display: "flex", alignItems: "center", justifyContent: "center", minHeight: "70vh", backgroundColor: "#f5f5f5" }}>
            <div style={{ textAlign: "center", padding: "30px", backgroundColor: "#fff", boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)", borderRadius: "10px", width: "350px" }}>
                <h2 style={{ fontSize: "24px", fontWeight: "bold", marginBottom: "20px" }}>{t("Contact.title")}</h2>

                <ContactItem
                    icon={Mail}
                    link={"mailto:" + cv.personal.mail}
                    text={cv.personal.mail}
                    color="#2563eb"
                />
                <ContactItem
                    icon={Linkedin}
                    link={cv.personal.linkedin}
                    text={cv.personal.nickname}
                    color="#0077B5"
                />
                <ContactItem
                    icon={Github}
                    link={cv.personal.github}
                    text={cv.personal.profile}
                    color="#333"
                />
                <ContactItem
                    icon={MapPin}
                    text={cv.personal.location}
                    color="#dc2626"
                />
            </div>
        </section>
    );
};

export default Contact;