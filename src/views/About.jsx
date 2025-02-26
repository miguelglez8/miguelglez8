import SectionItem from "../components/about/SectionItem.jsx";
import ExperienceItem from "../components/about/ExperienceItem.jsx";
import EducationItem from "../components/about/EducationItem.jsx";
import CourseItem from "../components/about/CourseItem.jsx";
import TechnologyItem from "../components/about/TechnologyItem.jsx";
import cvData from '../data/cvData';

const About = () => (
    <>
        <SectionItem title="Sobre Mí">
            <p style={{ fontSize: "18px", color: "#555", lineHeight: "1.6" }}>
                {cvData.info.description}
            </p>
        </SectionItem>

        <SectionItem title="Experiencia Laboral">
            <ul style={{ listStyleType: "none", paddingLeft: "0", marginTop: "20px" }}>
                {cvData.experiences.map((experience, index) => (
                    <ExperienceItem
                        key={index}
                        logo={experience.logo}
                        alt={experience.alt}
                        title={experience.title}
                        company={experience.company}
                        location={experience.location}
                        period={experience.period}
                        responsibilities={experience.responsibilities}
                    />
                ))}
            </ul>
        </SectionItem>

        <SectionItem title="Educación">
            <ul style={{ listStyleType: "none", paddingLeft: "0" }}>
                {cvData.education.map((edu, index) => (
                    <EducationItem
                        key={index}
                        logo={edu.logo}
                        alt={edu.alt}
                        title={edu.title}
                        institution={edu.institution}
                        location={edu.location}
                        period={edu.period}
                        details={edu.details}
                        link={edu.link}
                    />
                ))}
            </ul>
        </SectionItem>

        <SectionItem title="Cursos y Certificaciones">
            <ul style={{ listStyleType: "none", paddingLeft: "0" }}>
                {cvData.courses.map((course, index) => (
                    <CourseItem
                        key={index}
                        logo={course.logo}
                        alt={course.alt}
                        title={course.title}
                        date={course.date}
                        link={course.link}
                        linkText={course.linkText}
                    />
                ))}
            </ul>
        </SectionItem>

        <SectionItem title="Tecnologías">
            <div style={{ display: "flex", flexWrap: "wrap", gap: "10px", padding: "20px" }}>
                {cvData.technologies.map((tech, index) => (
                    <TechnologyItem
                        key={index}
                        name={tech.name}
                        backgroundColor={tech.backgroundColor}
                    />
                ))}
            </div>
        </SectionItem>
    </>
);

export default About;