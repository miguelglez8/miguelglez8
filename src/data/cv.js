import {COURSE_CATEGORY, JOB_STATUS, WORK_MODE} from "./constants.js";

const cv = {
    version: '1.0.0',
    created: '2025-02-26T08:00:00Z',
    updated: '2025-03-04T17:30:00Z',
    personal: {
        name: "Miguel González Navarro",
        location: "Moreda de Aller, Asturias",
        profile: "miguelglez8",
        mail: "miguemoreda@gmail.com",
        linkedin: "https://www.linkedin.com/in/miguel-gonzalez-navarro-full-stack-engineer",
        github: "https://github.com/miguelglez8",
        repo: "https://github.com/miguelglez8/miguelglez8",
        profileImage: "./images/foto.jpg"
    },
    info: {
        experiences: [
            {
                id: "exp1",
                logo: "./images/galeo.png",
                alt: "Logo GALEO",
                company: "GALEO",
                location: "Oviedo",
                workMode: WORK_MODE.remote
            },
            {
                id: "exp2",
                logo: "./images/hiberus.jpg",
                alt: "Logo Hiberus",
                company: "Hiberus",
                location: "Llanera",
                workMode: WORK_MODE.hybrid
            },
            {
                id: "exp3",
                logo: "./images/nttdata.png",
                alt: "Logo NTT DATA",
                company: "NTT DATA",
                location: "Oviedo",
                workMode: WORK_MODE.hybrid
            }
        ],
        education: [
            {
                id: "edu1",
                logo: "./images/uniovi.jpg",
                alt: "Logo Uni",
                location: "Oviedo",
                link: "https://catedraindra.uniovi.es/premios/2024.tfg-tfm"
            }
        ],
        courses: [
            {
                id: "course1",
                logo: "./images/uniovi.jpg",
                alt: "Logo Uni",
                link: "https://www.campusvirtual.uniovi.es/badges/badge.php?hash=1449850ff7707c0d5b1a095a0020646ff52f649b",
                category: COURSE_CATEGORY.certificate
            },
            {
                id: "course2",
                logo: "./images/prl.jpg",
                alt: "Logo PRL",
                category: COURSE_CATEGORY.course
            },
            {
                id: "course3",
                logo: "./images/dgt.jpg",
                alt: "Logo DGT",
                category: COURSE_CATEGORY.course
            }
        ],
        technologies: [
            { id: "java" },
            { id: "spring_boot" },
            { id: "react" },
            { id: "javascript" },
            { id: "rest_apis" },
            { id: "microservices" },
            { id: "kafka" },
            { id: "sql" },
            { id: "git" },
            { id: "docker" },
            { id: "kubernetes" },
            { id: "aws" },
            { id: "junit" },
            { id: "jsp" },
            { id: "liferay" }
        ],
    },
    projects: [
        {
            id: "project1",
            image: "./images/foto.jpg",
            status: JOB_STATUS.in_progress,
            technologies: [
                { text: "React" },
                { text: "JavaScript" },
                { text: "GitHub Pages" }
            ],
            links: [
                { href: "https://github.com/miguelglez8/miguelglez8", type: "github" },
                { href: "https://miguelglez8.github.io/miguelglez8", type: "web" }
            ]
        },
        {
            id: "project2",
            image: "./images/tfg.png",
            status: JOB_STATUS.finished,
            technologies: [
                { text: "React" },
                { text: "JavaScript" },
                { text: "Spring Boot" },
                { text: "Java" },
                { text: "Docker" },
                { text: "AWS" }
            ],
            links: [
                { href: "https://github.com/miguelglez8/project-tfg", type: "github" },
                { href: "https://digibuo.uniovi.es/dspace/handle/10651/76268", type: "doc" }
            ]
        },
        {
            id: "project3",
            image: "./images/footmatch.jpg",
            status: JOB_STATUS.finished,
            technologies: [
                { text: "Java" },
                { text: "Kotlin" },
            ],
            links: [
                { href: "https://github.com/miguelglez8/footmatch-sdm", type: "github" }
            ]
        },
        {
            id: "project4",
            image: "./images/nemorastur.png",
            status: JOB_STATUS.current,
            technologies: [
                { text: "Node" },
                { text: "JavaScript" },
                { text: "MongoDB" },
                { text: "Docker" }
            ],
            links: [
                { href: "https://github.com/miguelglez8/app-nemorastur", type: "github" }
            ]
        },
        {
            id: "project5",
            image: "./images/lomap.jpg",
            status: JOB_STATUS.finished,
            technologies: [
                { text: "React" },
                { text: "Node" },
                { text: "JavaScript" },
                { text: "SOLID" },
                { text: "CI/CD" }
            ],
            links: [
                { href: "https://github.com/miguelglez8/lomap_es5a", type: "github" },
                { href: "https://franciscocoya.github.io/lomapes05a_production", type: "web" },
                { href: "https://arquisoft.github.io/lomap_es5a", type: "doc" },
                { href: "https://www.youtube.com/watch?v=nkLxMZ8_4Dc", type: "demo" }
            ]
        }
    ]
};

export default cv;
