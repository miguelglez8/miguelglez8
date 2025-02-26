const ContactItem = ({ icon: Icon, link, text, color }) => (
    <p style={{ display: "flex", alignItems: "center", justifyContent: "center", gap: "10px", fontSize: "18px", marginBottom: "15px" }}>
        {link ? (
            <a href={link} style={{ display: "flex", alignItems: "center", gap: "8px", color, textDecoration: "none" }}>
                <Icon size={22} />
                {text}
            </a>
        ) : (
            <>
                <Icon size={22} style={{ color }} />
                {text}
            </>
        )}
    </p>
);

export default ContactItem;