import LinkedInIcon from "../icons/LinkedInIcon.jsx";
import GitHubIcon from "../icons/GitHubIcon.jsx";

const Init = () => (
    <section className="init flex items-center justify-center h-screen p-6 bg-white">
            <div className="flex flex-col justify-center text-left max-w-lg">
                    <h1 className="text-3xl font-bold">Miguel González Navarro</h1>
                    <h2 className="text-xl text-gray-600">Ingeniero de Software</h2>
                    <p className="text-gray-500 mt-2">Moreda de Aller, Asturias (España)</p>

                    <div className="flex gap-4 mt-4">
                            <a href="https://www.linkedin.com/in/miguelgonzaleznavarro">
                                    <LinkedInIcon size={40} color="#0077B5"/>
                            </a>
                            <a href="https://github.com/miguelglez8">
                                    <GitHubIcon size={40} color="#000000"/>
                            </a>
                    </div>
            </div>

            <div className="ml-8">
                    <img
                        src="/images/foto.jpg"
                        alt="Foto de Miguel González Navarro"
                        className="w-32 h-32 object-cover rounded-full shadow-lg"
                    />
            </div>
    </section>
);

export default Init;