import { useState, useEffect } from "react";
import { Mail, Github, Linkedin, MapPin } from "lucide-react";
import { useTranslation } from "react-i18next";
import ContactItem from "../components/contact/ContactItem.jsx";
import cv from "../data/cv.js";

const Contact = () => {
    const { t } = useTranslation();
    const [isMobile, setIsMobile] = useState(window.innerWidth <= 768);

    useEffect(() => {
        const handleResize = () => setIsMobile(window.innerWidth <= 768);
        window.addEventListener("resize", handleResize);
        return () => window.removeEventListener("resize", handleResize);
    }, []);

    return (
        <section
            style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
                backgroundColor: "#f5f5f5",
                padding: "20px",
                minHeight: "100vh",
            }}
        >
            <div
                style={{
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    justifyContent: "center",
                    backgroundColor: "#fff",
                    padding: isMobile ? "0" : "40px",
                    borderRadius: "10px",
                    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                    width: "50vh"
                }}
            >
                <h2 style={{ fontSize: "24px", fontWeight: "bold", marginBottom: "20px" }}>
                    {t("Contact.title")}
                </h2>

                <ContactItem icon={Mail} link={"mailto:" + cv.personal.mail} text={cv.personal.mail} color="#2563eb" />
                <ContactItem icon={Linkedin} link={cv.personal.linkedin} text={cv.personal.nickname} color="#0077B5" />
                <ContactItem icon={Github} link={cv.personal.github} text={cv.personal.profile} color="#333" />
                <ContactItem icon={MapPin} text={cv.personal.location} color="#dc2626" />
            </div>
        </section>
    );
};

export default Contact;
