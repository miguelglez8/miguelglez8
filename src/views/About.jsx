const About = () => (
    <section style={{padding: "20px", backgroundColor: "#f9f9f9"}}>
        <h2 style={{fontSize: "24px", fontWeight: "bold", marginBottom: "20px", textAlign: "center"}}>
            Información
        </h2>

        <section
            style={{
                display: "flex",
                flexDirection: "column",
                gap: "20px",
                padding: "20px",
            }}
        >
            <p style={{fontSize: "18px", color: "#555", lineHeight: "1.6"}}>
                Soy Ingeniero Técnico Informático de Software con amplia experiencia en el desarrollo de aplicaciones
                utilizando tecnologías como JavaScript, React, Docker, Spring, Java, JSP y HTML. Cuento con una sólida
                formación en matemáticas, programación, y diseño de sistemas, habiendo participado en proyectos que
                abarcan
                desde soluciones web hasta aplicaciones distribuidas y microservicios. Tengo experiencia en bases de
                datos
                relacionales y no relacionales, así como en plataformas de nube como AWS y Firebase, donde he diseñado y
                desplegado soluciones escalables y eficientes. Actualmente, me enfoco en el desarrollo web,
                especialmente en
                el backend, y participo en la creación de portales personalizados, integración de servicios, y
                optimización
                de la experiencia de usuario en Liferay. Estoy interesado en el diseño y desarrollo de sistemas
                eficientes y
                escalables, lo que me lleva a explorar el campo de la arquitectura digital. Apasionado por la innovación
                tecnológica y motivado por la resolución de problemas complejos, participo activamente en proyectos
                personales y colaborativos que buscan impulsar el progreso tecnológico.
            </p>
        </section>

        <section
            style={{
                display: "flex",
                flexDirection: "column",
                gap: "20px",
                padding: "20px",
            }}
        >
            <h3 style={{fontSize: "22px", fontWeight: "bold", textAlign: 'center'}}>
                Experiencia Laboral
            </h3>

            <ul style={{listStyleType: "none", paddingLeft: "0", marginTop: "20px"}}>
                <li style={{display: "flex", alignItems: "flex-start", marginBottom: "20px"}}>
                    <img
                        src="/images/hiberus.jpg"
                        alt="Logo de Hiberus"
                        style={{
                            width: "80px",
                            height: "80px",
                            borderRadius: "10px",
                            marginRight: "20px",
                        }}
                    />
                    <div style={{flex: 1}}>
                        <p style={{fontSize: "18px", fontWeight: "bold"}}>
                            Desarrollador de Software
                        </p>
                        <p style={{fontSize: "16px", color: "#555", marginBottom: "10px"}}>
                            Hiberus, Oviedo
                        </p>
                        <ul style={{paddingLeft: "20px", fontSize: "16px", color: "#555"}}>
                            <li>Desarrollo de portales personalizados en Liferay 7.4 para el proyecto Vitaly, integrando
                                servicios y optimizando la experiencia del usuario
                            </li>
                            <li>Resolución de incidencias y mejora continua de las funcionalidades del portal</li>
                        </ul>

                        <div style={{display: "flex", justifyContent: "space-between", marginTop: "10px"}}>
                            <span style={{fontSize: "14px", color: "#888"}}>Octubre 2024 - Actualidad</span>
                        </div>
                    </div>
                </li>

                <li style={{display: "flex", alignItems: "flex-start", marginBottom: "20px"}}>
                    <img
                        src="/images/nttdata.png"
                        alt="Logo de NTTDATA"
                        style={{
                            width: "80px",
                            height: "80px",
                            borderRadius: "10px",
                            marginRight: "20px",
                        }}
                    />
                    <div style={{flex: 1}}>
                        <p style={{fontSize: "18px", fontWeight: "bold"}}>Estudiante de Arquitectura Digital</p>
                        <p style={{fontSize: "16px", color: "#555", marginBottom: "10px"}}>NTTDATA, Oviedo</p>
                        <ul style={{paddingLeft: "20px", fontSize: "16px", color: "#555"}}>
                            <li>Prácticas de Ingeniería Informática especializadas en desarrollo backend con Java y
                                Spring Boot
                            </li>
                            <li>Desarrollo de APIs Restful y microservicios con arquitectura escalable</li>
                            <li>Aplicación de patrones de diseño para una estructura de código eficiente</li>
                            <li>Optimización del código y ejecución de pruebas de carga para asegurar el rendimiento
                            </li>
                        </ul>

                        <div style={{display: "flex", justifyContent: "space-between", marginTop: "10px"}}>
                            <span style={{fontSize: "14px", color: "#888"}}>Marzo 2024 - Julio 2024</span>
                        </div>
                    </div>
                </li>
            </ul>
        </section>

        <section
            style={{
                display: "flex",
                flexDirection: "column",
                gap: "20px",
                padding: "20px",
            }}
        >
            <h3 style={{fontSize: "22px", fontWeight: "bold", textAlign: "center"}}>Educación</h3>
            <ul style={{listStyleType: "none", paddingLeft: "0"}}>
                <li style={{display: "flex", alignItems: "flex-start", marginBottom: "20px"}}>
                    <img
                        src="/images/uniovi.jpg"
                        alt="Logo Universidad de Oviedo"
                        style={{
                            width: "80px",
                            height: "80px",
                            borderRadius: "10px",
                            marginRight: "20px",
                        }}
                    />
                    <div style={{flex: 1}}>
                        <p style={{fontSize: "18px", fontWeight: "bold"}}>Grado en Ingeniería Informática de
                            Software</p>
                        <p style={{fontSize: "16px", color: "#555", marginBottom: "10px"}}>Universidad de Oviedo, Oviedo</p>
                        <ul style={{paddingLeft: "20px", fontSize: "16px", color: "#555"}}>
                            <li>Promedio general de calificaciones: 8,16</li>
                            <li>He completado el grado en 4 años, aprobando todas las asignaturas en primera matrícula
                                (con 3 MH, 11 sobresalientes y 18 notables)
                            </li>
                            <li>MH: Ondas y Electromagnetismo, Estadística y Computabilidad</li>
                            <li>También obtuve un 10 en mi Trabajo de Fin de Grado (TFG), titulado &#39;Plataforma Web
                                para la coordinación de trabajos académicos&#39;
                            </li>
                            <li>
                                Reconocimiento otorgado por la Cátedra Indra y la Universidad de Oviedo al mejor Trabajo Fin de Grado/Máster en el año 2023/2024
                                (<a href="https://catedraindra.uniovi.es/" target="_blank" style={{ color: "#2563eb", fontWeight: "bold" }}>
                                    Más información
                                </a>)
                            </li>
                        </ul>
                        <div style={{display: "flex", justifyContent: "space-between", marginTop: "10px"}}>
                            <span style={{fontSize: "14px", color: "#888"}}>Septiembre 2020 - Julio 2024</span>
                        </div>
                    </div>
                </li>

                <li style={{display: "flex", alignItems: "flex-start", marginBottom: "20px"}}>
                    <img
                        src="/images/iesvalledealler.jpg"
                        alt="Logo IES Valle de Aller"
                        style={{
                            width: "80px",
                            height: "80px",
                            borderRadius: "10px",
                            marginRight: "20px",
                        }}
                    />
                    <div style={{flex: 1}}>
                        <p style={{fontSize: "18px", fontWeight: "bold"}}>Bachillerato Científico-Tecnológico</p>
                        <p style={{fontSize: "16px", color: "#555", marginBottom: "10px"}}>IES Valle de Aller, Moreda de Aller</p>
                        <ul style={{paddingLeft: "20px", fontSize: "16px", color: "#555"}}>
                            <li>Promedio general de calificaciones: 9,875</li>
                            <li>Graduación con honores</li>
                            <li>Menciones en Matemáticas y Física</li>
                            <li>Nota EBAU: 13,368</li>
                        </ul>
                        <div style={{display: "flex", justifyContent: "space-between", marginTop: "10px"}}>
                        <span style={{fontSize: "14px", color: "#888"}}>Septiembre 2018 - Junio 2020</span>
                        </div>
                    </div>
                </li>
            </ul>

        </section>

        <section
            style={{
                display: "flex",
                flexDirection: "column",
                gap: "20px",
                padding: "20px",
            }}
        >
            <h3 style={{fontSize: "22px", fontWeight: "bold", textAlign: "center"}}>
                Cursos y Certificaciones
            </h3>

            <ul style={{listStyleType: "none", paddingLeft: "0"}}>
                <li style={{display: "flex", alignItems: "center", marginBottom: "20px"}}>
                    <img
                        src="/images/liferay.png"
                        alt="Logo Liferay"
                        style={{width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px"}}
                    />
                    <div style={{flex: 1}}>
                        <p style={{fontSize: "18px", fontWeight: "bold"}}>
                            Liferay DXP 7.4 Frontend & Backend Advanced Developer
                        </p>
                        <p style={{fontSize: "16px", color: "#555"}}>
                            📅 <strong>Expedición:</strong> Noviembre 2024
                        </p>
                        <p style={{fontSize: "16px", color: "#555"}}>
                            🔗 <a href="https://learn.liferay.com/w/reference/liferay-university" target="_blank"
                                 style={{color: "#2563eb"}}>Ver curso</a>
                        </p>
                    </div>
                </li>

                <li style={{display: "flex", alignItems: "center", marginBottom: "20px"}}>
                    <img
                        src="/images/udemy.png"
                        alt="Logo Udemy"
                        style={{width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px"}}
                    />
                    <div style={{flex: 1}}>
                        <p style={{fontSize: "18px", fontWeight: "bold"}}>
                            Event-Driven Microservices, CQRS, SAGA, Axon, Spring Boot
                        </p>
                        <p style={{fontSize: "16px", color: "#555"}}>
                            📅 <strong>Expedición:</strong> Mayo 2024
                            </p>
                            <p style={{fontSize: "16px", color: "#555"}}>
                                🔗 <a
                                href="https://nttdatalearn.udemy.com/certificate/UC-8d4bae7c-940d-4cde-af62-9bbf33875be5/"
                                target="_blank" style={{color: "#2563eb"}}>Ver certificado</a>
                            </p>
                        </div>
                    </li>

                    <li style={{display: "flex", alignItems: "center", marginBottom: "20px"}}>
                        <img
                            src="/images/udemy.png"
                            alt="Logo Udemy"
                            style={{width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px"}}
                        />
                        <div style={{flex: 1}}>
                            <p style={{fontSize: "18px", fontWeight: "bold"}}>
                                Fundamentos en DevOps, APIs y Arquitectura de Microservicios
                            </p>
                            <p style={{fontSize: "16px", color: "#555"}}>
                                📅 <strong>Expedición:</strong> Abril 2024
                            </p>
                            <p style={{fontSize: "16px", color: "#555"}}>
                                🔗 <a
                                href="https://nttdatalearn.udemy.com/certificate/UC-65cf7da0-97d6-4671-a4ef-7d3040236c6b/"
                                target="_blank" style={{color: "#2563eb"}}>Ver certificado</a>
                            </p>
                        </div>
                    </li>

                    <li style={{display: "flex", alignItems: "center", marginBottom: "20px"}}>
                        <img
                            src="/images/uniovi.jpg"
                            alt="Logo Universidad"
                            style={{width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px"}}
                        />
                        <div style={{flex: 1}}>
                            <p style={{fontSize: "18px", fontWeight: "bold"}}>
                                Curso de Grado en Seguridad de Sistemas Informáticos
                            </p>
                            <p style={{fontSize: "16px", color: "#555"}}>
                                📅 <strong>Expedición:</strong> Junio 2023
                            </p>
                            <p style={{fontSize: "16px", color: "#555"}}>
                                🔗 <a
                                href="https://www.campusvirtual.uniovi.es/badges/badge.php?hash=1449850ff7707c0d5b1a095a0020646ff52f649b"
                                target="_blank" style={{color: "#2563eb"}}>Ver certificado</a>
                            </p>
                        </div>
                    </li>

                    <li style={{display: "flex", alignItems: "center", marginBottom: "20px"}}>
                        <img
                            src="/images/prl.jpg"
                            alt="Logo"
                            style={{width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px"}}
                        />
                        <div style={{flex: 1}}>
                            <p style={{fontSize: "18px", fontWeight: "bold"}}>
                                Prevención de Riesgos Laborales de Personal de Oficinas
                            </p>
                            <p style={{fontSize: "16px", color: "#555"}}>
                                📅 <strong>Expedición:</strong> Noviembre 2024
                            </p>
                        </div>
                    </li>

                    <li style={{display: "flex", alignItems: "center", marginBottom: "20px"}}>
                        <img
                            src="/images/dgt.jpg"
                            alt="Logo"
                            style={{width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px"}}
                        />
                        <div style={{flex: 1}}>
                            <p style={{fontSize: "18px", fontWeight: "bold"}}>
                                Permiso de Conducir B
                            </p>
                            <p style={{fontSize: "16px", color: "#555"}}>
                                📅 <strong>Expedición:</strong> Agosto 2021
                            </p>
                        </div>
                    </li>
                </ul>
            </section>

            <section
                style={{
                    display: "flex",
                    flexDirection: "column",
                    gap: "20px",
                    padding: "20px",
                }}
            >
                <h3 style={{fontSize: "24px", fontWeight: "bold", textAlign: "center"}}>Tecnologías</h3>
                <div
                    style={{
                        display: "flex",
                        flexWrap: "wrap",
                        gap: "10px",
                    }}
                >
                <span
                    style={{
                        backgroundColor: "#F97316",
                        color: "#fff",
                        padding: "8px 16px",
                        borderRadius: "20px",
                        fontSize: "14px",
                    }}
                >
                    JavaScript
                </span>
                    <span
                        style={{
                            backgroundColor: "#34D399",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    React
                </span>
                    <span
                        style={{
                            backgroundColor: "#F59E0B",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    HTML
                </span>

                    <span
                        style={{
                            backgroundColor: "#2563eb",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    Java
                </span>
                    <span
                        style={{
                            backgroundColor: "#34D399",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    Spring Boot
                </span>
                    <span
                        style={{
                            backgroundColor: "#22D3EE",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    Liferay
                </span>

                    <span
                        style={{
                            backgroundColor: "#F97316",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    Docker
                </span>
                    <span
                        style={{
                            backgroundColor: "#D1D5DB",
                            color: "#333",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    AWS
                </span>
                    <span
                        style={{
                            backgroundColor: "#FBBF24",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    Firebase
                </span>

                    <span
                        style={{
                            backgroundColor: "#8B5CF6",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    Desarrollo de API
                </span>
                    <span
                        style={{
                            backgroundColor: "#34D399",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    Bases de datos (SQL, MongoDB)
                </span>
                    <span
                        style={{
                            backgroundColor: "#F59E0B",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    Microservicios
                </span>

                    <span
                        style={{
                            backgroundColor: "#2563eb",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    Git
                </span>
                    <span
                        style={{
                            backgroundColor: "#34D399",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    GitHub
                </span>

                    <span
                        style={{
                            backgroundColor: "#2563eb",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    Android
                </span>
                    <span
                        style={{
                            backgroundColor: "#9D174D",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    Kotlin
                </span>

                    <span
                        style={{
                            backgroundColor: "#F97316",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    JUnit
                </span>
                    <span
                        style={{
                            backgroundColor: "#8B5CF6",
                            color: "#fff",
                            padding: "8px 16px",
                            borderRadius: "20px",
                            fontSize: "14px",
                        }}
                    >
                    JMeter
                </span>
                </div>
            </section>
        </section>
        );

        export default About;