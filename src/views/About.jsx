import { useTranslation } from "react-i18next";
import SectionItem from "../components/about/SectionItem.jsx";
import ExperienceItem from "../components/about/ExperienceItem.jsx";
import EducationItem from "../components/about/EducationItem.jsx";
import CourseItem from "../components/about/CourseItem.jsx";
import TechnologyItem from "../components/about/TechnologyItem.jsx";
import cv from '../data/cv.js';
import {COURSE_CATEGORY} from "../data/constants.js";

const About = () => {
    const { t } = useTranslation();

    const getCourseLinkText = (category) => {
        switch (category) {
            case COURSE_CATEGORY.course:
                return t('About.courses.course');
            case COURSE_CATEGORY.certificate:
                return t('About.courses.certificate');
            default:
                return '';
        }
    };

    return (
        <>
            <SectionItem title={t("About.aboutMe")}>
                <p style={{ fontSize: "18px", color: "#555", lineHeight: "1.6" }}>
                    {t("About.description")}
                </p>
            </SectionItem>

            <SectionItem title={t("About.experiences.title")}>
                <ul style={{ listStyleType: "none", paddingLeft: "0", marginTop: "20px" }}>
                    {cv.info.experiences.map((experience, index) => {
                        const responsibilities = Object.values(
                            t(`About.experiences.${experience.id}.responsibilities`, { returnObjects: true })
                        ).map((resp) => resp.text);

                        return (
                            <ExperienceItem
                                key={index}
                                logo={experience.logo}
                                alt={experience.alt}
                                title={t(`About.experiences.${experience.id}.title`)}
                                company={experience.company}
                                location={experience.location}
                                period={t(`About.experiences.${experience.id}.period`)}
                                responsibilities={responsibilities}
                            />
                        );
                    })}
                </ul>
            </SectionItem>

            <SectionItem title={t("About.education.title")}>
                <ul style={{ listStyleType: "none", paddingLeft: "0" }}>
                    {cv.info.education.map((edu, index) => {
                        const details = Object.values(
                            t(`About.education.${edu.id}.details`, { returnObjects: true })
                        ).map((resp) => resp.text);

                        return (
                            <EducationItem
                                key={index}
                                logo={edu.logo}
                                alt={edu.alt}
                                title={t(`About.education.${edu.id}.title`)}
                                institution={t(`About.education.${edu.id}.institution`)}
                                location={edu.location}
                                period={t(`About.education.${edu.id}.period`)}
                                details={details}
                                link={edu.link}
                                text={t("About.education.moreInfo")}
                            />
                        );
                    })}
                </ul>
            </SectionItem>

            <SectionItem title={t("About.courses.title")}>
                <ul style={{ listStyleType: "none", paddingLeft: "0" }}>
                    {cv.info.courses.map((course, index) => (
                        <CourseItem
                            key={index}
                            logo={course.logo}
                            alt={course.alt}
                            title={t(`About.courses.${course.id}.title`)}
                            organization={t(`About.courses.${course.id}.organization`)}
                            date={t(`About.courses.${course.id}.date`)}
                            link={course.link}
                            linkText={getCourseLinkText(course.category)}
                            text={t("About.courses.issued")}
                        />
                    ))}
                </ul>
            </SectionItem>

            <SectionItem title={t("About.technologies")}>
                <div style={{ display: "flex", flexWrap: "wrap", gap: "10px", padding: "20px" }}>
                    {cv.info.technologies.map((tech, index) => (
                        <TechnologyItem
                            key={index}
                            name={tech.name}
                        />
                    ))}
                </div>
            </SectionItem>
        </>
    );
};

export default About;
