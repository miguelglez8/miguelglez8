import ProjectLinks from "./ProjectLinks.jsx";
import Badge from "./Badge.jsx";

const ProjectCard = ({ image, title, role, date, status, statusColor, technologies, links, statusIcon, isMobile }) => {
    return (
        <div
            style={{
                width: isMobile ? "100%" : "48%",
                backgroundColor: "#fff",
                borderRadius: "10px",
                boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
                transition: "transform 0.3s ease-in-out",
                marginBottom: "20px"
            }}
        >
            <div style={{ display: "flex", alignItems: "center", padding: "20px" }}>
                <div
                    style={{
                        backgroundImage: `url(${image})`,
                        backgroundSize: "cover",
                        backgroundPosition: "center",
                        backgroundRepeat: "no-repeat",
                        height: "15vh",
                        width: "15vw",
                        maxHeight: "120px",
                        maxWidth: "120px",
                        minHeight: "80px",
                        minWidth: "80px",
                        borderRadius: "10px",
                        marginRight: "20px"
                    }}
                ></div>

                <h3 style={{ fontSize: "24px", fontWeight: "bold", textAlign: "center", flex: 1 }}>
                    {title}
                </h3>
            </div>

            <div
                style={{
                    padding: "20px",
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "space-between",
                    height: "45vh"
                }}
            >
                <h4 style={{ fontSize: "18px", fontWeight: "bold" }}>{role}</h4>

                <div style={{ display: "flex", alignItems: "center", gap: "10px", marginBottom: "15px" }}>
                    <p style={{ fontSize: "18px", color: "#777" }}>{date}</p>
                    <Badge text={status} color={statusColor} />
                    {statusIcon}
                </div>

                <div style={{ display: "flex", flexWrap: "wrap", gap: "10px", marginBottom: "20px" }}>
                    {technologies.map((tech, index) => (
                        <Badge key={index} text={tech.text} color={tech.color} />
                    ))}
                </div>

                <ProjectLinks links={links} />
            </div>
        </div>
    );
};

export default ProjectCard;
