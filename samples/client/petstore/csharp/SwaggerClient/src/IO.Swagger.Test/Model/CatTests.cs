using NUnit.Framework;

using System;
using System.Linq;
using System.IO;
using System.Collections.Generic;
using IO.Swagger.Api;
using IO.Swagger.Model;
using IO.Swagger.Client;
using System.Reflection;

namespace IO.Swagger.Test
{
    /// <summary>
    ///  Class for testing Cat
    /// </summary>
    /// <remarks>
    /// This file is automatically generated by Swagger Codegen.
    /// Please update the test case below to test the model.
    /// </remarks>
    [TestFixture]
    public class CatTests
    {
        private Cat instance;

        /// <summary>
        /// Setup before each test
        /// </summary>
        [SetUp]
        public void Init()
        {
			instance = new Cat(ClassName: "csharp test");
        }

        /// <summary>
        /// Clean up after each test
        /// </summary>
        [TearDown]
        public void Cleanup()
        {

        }

        /// <summary>
        /// Test an instance of Cat
        /// </summary>
        [Test]
        public void CatInstanceTest()
        {
            Assert.IsInstanceOf<Cat> (instance, "instance is a Cat");
        }

        /// <summary>
        /// Test the property 'ClassName'
        /// </summary>
        [Test]
        public void ClassNameTest()
        {
            // TODO: unit test for the property 'ClassName'
        }
        /// <summary>
        /// Test the property 'Declawed'
        /// </summary>
        [Test]
        public void DeclawedTest()
        {
            // TODO: unit test for the property 'Declawed'
        }

    }

}
