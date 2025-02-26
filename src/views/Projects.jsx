import ProjectCard from "../components/projects/ProjectCard.jsx";
import cvData from "../data/cvData.js";

const Projects = () => (
    <section
        style={{
            display: "flex",
            flexDirection: "column",
            gap: "20px",
            padding: "20px",
            alignItems: "center",
            justifyContent: "center"
        }}
    >
        <h2 style={{
            fontSize: "24px",
            fontWeight: "bold",
            marginBottom: "20px",
            textAlign: "center",
            width: "100%",
        }}>
            ¡Consulta mis proyectos!
        </h2>

        <div
            style={{
                display: "flex",
                flexWrap: "wrap",
                gap: "20px",
                justifyContent: "space-between",
                width: "100%",
            }}
        >
            {cvData.projects.map((project, index) => (
                <ProjectCard
                    key={index}
                    image={project.image}
                    title={project.title}
                    role={project.role}
                    date={project.date}
                    status={project.status}
                    statusColor={project.statusColor}
                    technologies={project.technologies}
                    links={project.links}
                />
            ))}
        </div>

    </section>
);

export default Projects;